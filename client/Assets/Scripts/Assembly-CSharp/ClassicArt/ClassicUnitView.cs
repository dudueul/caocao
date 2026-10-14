using System;
using System.Collections;
using UnityEngine;

namespace Rebuild.Classic
{
    public enum ClassicFacing { Down, Up, Left, Right }

    // Animates one unit with the 1998 sprite sheets. Logical animations:
    //   directional: walk, stand, attack, guard   (down / up / side; side frames face
    //                left and are mirrored for Right)
    //   single:      weak (low HP), hurt, cheer
    [RequireComponent(typeof(SpriteRenderer))]
    public class ClassicUnitView : MonoBehaviour
    {
        public int job = 3;                 // jobs.csv id
        public ClassicSide side = ClassicSide.Player;
        public int officer = -1;            // officers.csv id for unique sprites, -1 = none
        public ClassicFacing facing = ClassicFacing.Down;
        public string anim = "stand";

        [Tooltip("Frames per second for walk/weak loops; attacks play at twice this.")]
        public float fps = 4f;

        [Tooltip("Sort units by screen row so lower units draw in front.")]
        public bool sortByRow = true;

        public ClassicSpriteSet Set { get; private set; }
        public bool Playing { get { return frames != null && (loop || index < frames.Length - 1); } }

        SpriteRenderer sr;
        Sprite[] frames;
        bool loop;
        int index;
        float timer, frameTime;
        Action onDone;

        void Awake()
        {
            sr = GetComponent<SpriteRenderer>();
        }

        void Start()
        {
            Refresh();
        }

        // Re-resolves the sprite set after job / side / officer changed.
        public void Refresh()
        {
            Set = ClassicArt.SpriteSetFor(job, side, officer);
            if (Set == null)
            {
                UnityEngine.Debug.LogWarning("[ClassicUnitView] no sprite set for job " + job + " side " + side);
                return;
            }
            Play(anim, facing);
        }

        public void Face(ClassicFacing f)
        {
            facing = f;
            Play(anim, f);
        }

        // Plays `logical` (walk, stand, attack, guard, weak, hurt, cheer) facing `f`.
        // Attacks play once and call onComplete, then return to stand.
        public void Play(string logical, ClassicFacing f, Action onComplete = null)
        {
            if (sr == null) sr = GetComponent<SpriteRenderer>();
            anim = logical;
            facing = f;
            if (Set == null) return;

            bool directional = logical == "walk" || logical == "stand" || logical == "attack" || logical == "guard";
            string dir = f == ClassicFacing.Down ? "down" : f == ClassicFacing.Up ? "up" : "side";
            var a = ClassicArt.Anim(directional ? logical + "_" + dir : logical);
            if (a == null)
            {
                UnityEngine.Debug.LogWarning("[ClassicUnitView] unknown animation " + logical);
                return;
            }
            var sheet = Set.Sheet(a.sheet);
            frames = new Sprite[a.frames.Length];
            for (int i = 0; i < frames.Length; i++)
                frames[i] = sheet[Mathf.Clamp(a.frames[i], 0, sheet.Length - 1)];

            loop = logical != "attack";
            frameTime = 1f / Mathf.Max(0.1f, logical == "attack" ? fps * 2f : fps);
            onDone = onComplete;
            index = 0;
            timer = 0f;
            sr.flipX = directional && f == ClassicFacing.Right;
            sr.sprite = frames[0];
        }

        // Walks to `target` (world space) along a straight line at `tilesPerSecond`,
        // facing the dominant axis, then stands.
        public IEnumerator WalkTo(Vector3 target, float tilesPerSecond = 3f)
        {
            Vector3 d = target - transform.position;
            if (d.sqrMagnitude < 1e-6f) yield break;
            var f = Mathf.Abs(d.x) > Mathf.Abs(d.y)
                ? (d.x > 0 ? ClassicFacing.Right : ClassicFacing.Left)
                : (d.y > 0 ? ClassicFacing.Up : ClassicFacing.Down);
            Play("walk", f);
            while ((transform.position - target).sqrMagnitude > 1e-6f)
            {
                transform.position = Vector3.MoveTowards(transform.position, target, tilesPerSecond * Time.deltaTime);
                yield return null;
            }
            Play("stand", f);
        }

        void Update()
        {
            if (frames == null || frames.Length == 0) return;
            if (frames.Length > 1)
            {
                timer += Time.deltaTime;
                while (timer >= frameTime)
                {
                    timer -= frameTime;
                    if (index < frames.Length - 1) index++;
                    else if (loop) index = 0;
                    else
                    {
                        var done = onDone;
                        onDone = null;
                        Play("stand", facing);
                        if (done != null) done();
                        return;
                    }
                    sr.sprite = frames[index];
                }
            }
        }

        void LateUpdate()
        {
            if (sortByRow && sr != null)
                sr.sortingOrder = 100 + Mathf.RoundToInt(-transform.position.y * 4f);
        }
    }
}
