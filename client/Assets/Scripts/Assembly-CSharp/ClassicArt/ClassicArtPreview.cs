using System.Collections;
using System.Collections.Generic;
using UnityEngine;

namespace Rebuild.Classic
{
    // Drop on an empty GameObject in an empty scene and press Play to check the
    // exported 1998 art: a battle map with one unit per class walking, attacking,
    // guarding and cheering.
    //   ← / →  previous / next map      Space  everybody attacks
    //   1 2 3  enemy / ally / player colours   G  portrait + item gallery
    //   mouse  terrain of the tile under the cursor
    public class ClassicArtPreview : MonoBehaviour
    {
        public int mapId;
        [Range(1, 60)] public int maxUnits = 30;
        public float actionInterval = 2.5f;

        // 평지 초원 황무지 다리 성내 마을 (terrain.csv ids) — ground every class can stand on.
        static readonly int[] OpenGround = { 0, 1, 3, 8, 16, 21 };

        ClassicBattleMapView map;
        readonly List<ClassicUnitView> units = new List<ClassicUnitView>();
        readonly HashSet<int> occupied = new HashSet<int>();
        Camera cam;
        bool gallery;
        int galleryPage;
        string hover = "";

        void Start()
        {
            if (!ClassicArt.Available) return;   // OnGUI explains how to export
            cam = Camera.main;
            if (cam == null)
            {
                cam = new GameObject("Main Camera").AddComponent<Camera>();
                cam.tag = "MainCamera";
            }
            cam.orthographic = true;
            cam.clearFlags = CameraClearFlags.SolidColor;
            cam.backgroundColor = new Color(0.12f, 0.12f, 0.12f);

            map = new GameObject("Map").AddComponent<ClassicBattleMapView>();
            map.loadOnStart = false;
            map.transform.SetParent(transform, false);
            ShowMap(mapId);
            StartCoroutine(Act());
        }

        void ShowMap(int id)
        {
            var maps = ClassicArt.Manifest.maps;
            if (maps == null || maps.Length == 0) return;
            id = ((id % maps.Length) + maps.Length) % maps.Length;
            if (!map.Load(maps[id].id)) return;
            mapId = id;
            FitCamera();
            SpawnUnits();
        }

        void FitCamera()
        {
            var b = map.WorldBounds;
            cam.transform.position = new Vector3(b.center.x, b.center.y, -10f);
            cam.orthographicSize = Mathf.Max(b.extents.y, b.extents.x / Mathf.Max(0.1f, cam.aspect)) + 0.5f;
        }

        void SpawnUnits()
        {
            foreach (var u in units) if (u != null) Destroy(u.gameObject);
            units.Clear();
            occupied.Clear();
            var jobs = ClassicArt.Manifest.jobs;
            if (jobs == null) return;

            var free = new List<int>();
            for (int y = 0; y < map.Height; y++)
                for (int x = 0; x < map.Width; x++)
                    if (System.Array.IndexOf(OpenGround, map.TerrainAt(x, y)) >= 0) free.Add(y * map.Width + x);
            var rng = new System.Random(mapId);
            for (int i = 0; i < jobs.Length && units.Count < maxUnits && free.Count > 0; i++)
            {
                int k = rng.Next(free.Count), cell = free[k];
                free.RemoveAt(k);
                var go = new GameObject(jobs[i].name);
                go.transform.SetParent(transform, false);
                go.AddComponent<SpriteRenderer>();
                var u = go.AddComponent<ClassicUnitView>();
                u.job = jobs[i].job;
                u.side = (ClassicSide)(i % 3);
                u.facing = (ClassicFacing)rng.Next(4);
                go.transform.position = map.TileToWorld(cell % map.Width, cell / map.Width);
                occupied.Add(cell);
                units.Add(u);
            }
        }

        IEnumerator Act()
        {
            var rng = new System.Random();
            while (true)
            {
                yield return new WaitForSeconds(actionInterval);
                foreach (var u in units)
                {
                    if (u == null || u.Set == null) continue;
                    int roll = rng.Next(10);
                    if (roll < 5) TryStep(u, rng);
                    else if (roll < 7) u.Play("attack", u.facing);
                    else if (roll < 8) u.Play("guard", u.facing);
                    else if (roll < 9) u.Play(rng.Next(2) == 0 ? "hurt" : "weak", u.facing);
                    else u.Play("cheer", u.facing);
                }
            }
        }

        void TryStep(ClassicUnitView u, System.Random rng)
        {
            int x, y;
            if (!map.WorldToTile(u.transform.position, out x, out y)) return;
            int d = rng.Next(4);
            int nx = x + (d == 2 ? -1 : d == 3 ? 1 : 0), ny = y + (d == 0 ? 1 : d == 1 ? -1 : 0);
            int cell = ny * map.Width + nx;
            if (System.Array.IndexOf(OpenGround, map.TerrainAt(nx, ny)) < 0 || occupied.Contains(cell)) return;
            occupied.Remove(y * map.Width + x);
            occupied.Add(cell);
            StartCoroutine(u.WalkTo(map.TileToWorld(nx, ny), 2f));
        }

        void Update()
        {
            if (map == null) return;
            if (Input.GetKeyDown(KeyCode.RightArrow)) ShowMap(mapId + 1);
            if (Input.GetKeyDown(KeyCode.LeftArrow)) ShowMap(mapId - 1);
            if (Input.GetKeyDown(KeyCode.G)) gallery = !gallery;
            if (gallery && Input.GetKeyDown(KeyCode.PageDown)) galleryPage++;
            if (gallery && Input.GetKeyDown(KeyCode.PageUp)) galleryPage = Mathf.Max(0, galleryPage - 1);
            if (Input.GetKeyDown(KeyCode.Space))
                foreach (var u in units) if (u != null) u.Play("attack", u.facing);
            for (int s = 0; s < 3; s++)
                if (Input.GetKeyDown(KeyCode.Alpha1 + s))
                    foreach (var u in units) if (u != null) { u.side = (ClassicSide)s; u.Refresh(); }

            int x, y;
            var w = cam.ScreenToWorldPoint(Input.mousePosition);
            hover = map.WorldToTile(w, out x, out y)
                ? string.Format("({0},{1}) {2}", x, y, map.TerrainNameAt(x, y)) : "";
        }

        void OnGUI()
        {
            if (!ClassicArt.Available)
            {
                GUI.Label(new Rect(20, 20, 900, 80),
                    "Classic art not exported.\nRun: python3 -I scripts/ekd5/export_art.py <1998 조조전 folder>\n" +
                    "then return to Unity (see docs/re/CLASSIC_ART.md).");
                return;
            }
            var g = map != null ? map.Grid : null;
            string title = g != null ? string.Format("Map {0:00} {1}  {2}x{3}", g.id, g.name, g.width, g.height) : "";
            GUI.Box(new Rect(10, 10, 430, 46), "");
            GUI.Label(new Rect(18, 12, 420, 22), title + "   " + hover);
            GUI.Label(new Rect(18, 32, 420, 22), "←/→ map   Space attack   1/2/3 colours   G gallery");
            if (gallery) DrawGallery();
        }

        void DrawGallery()
        {
            var m = ClassicArt.Manifest;
            const int perPage = 40;
            int first = galleryPage * perPage;
            GUI.Box(new Rect(10, 60, 10 * 68 + 10, 4 * 84 + 70), "faces " + first + "–" + (first + perPage - 1) + "  (PgUp/PgDn)");
            for (int i = 0; i < perPage && first + i < m.faces; i++)
            {
                var s = ClassicArt.Face(first + i);
                if (s != null) GUI.DrawTexture(new Rect(15 + (i % 10) * 68, 80 + (i / 10) * 84, 64, 80), s.texture);
            }
            for (int i = 0; i < 20 && i < m.items; i++)
            {
                var s = ClassicArt.ItemIcon((galleryPage * 20 + i) % m.items);
                if (s != null) GUI.DrawTexture(new Rect(15 + i * 34, 80 + 4 * 84 + 8, 32, 32), s.texture);
            }
        }
    }
}
