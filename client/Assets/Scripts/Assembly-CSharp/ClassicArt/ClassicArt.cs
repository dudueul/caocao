using System;
using System.Collections.Generic;
using UnityEngine;

namespace Rebuild.Classic
{
    // Placeholder art from the 1998 PC 조조전, exported locally by
    // scripts/ekd5/export_art.py into Assets/Resources/ClassicArt (git-ignored).
    // See docs/re/CLASSIC_ART.md. All shapes below mirror classic_art.json and
    // maps/map_##_grid.json so JsonUtility can read them.

    [Serializable]
    public class ClassicAnim
    {
        public string name;   // walk_down, stand_side, attack_up, guard_down, hurt, cheer, weak ...
        public string sheet;  // mov | atk | spc
        public int[] frames;
    }

    [Serializable]
    public class ClassicSpriteRef
    {
        public int job = -1;      // jobs.csv id (job entries)
        public int officer = -1;  // officers.csv id (officer overrides)
        public string name;
        public int set;           // UNIT_*.E5 member
        public int colors;        // 3 = enemy/ally/player triplet starting at `set`, 1 = one set for all sides
        public string confidence; // high | medium | low | unverified
    }

    [Serializable]
    public class ClassicMapInfo
    {
        public int id;
        public string name;
        public int width;
        public int height;
    }

    [Serializable]
    public class ClassicManifest
    {
        public string source;
        public int tileSize = 48;
        public int moveFrame = 48, moveFrames = 11;
        public int attackFrame = 64, attackFrames = 12;
        public int specialFrame = 48, specialFrames = 5;
        public int unitSets, faces, items, backgrounds;
        public ClassicMapInfo[] maps;
        public string[] sfx;
        public ClassicAnim[] anims;
        public ClassicSpriteRef[] jobs;
        public ClassicSpriteRef[] officers;
    }

    [Serializable]
    public class ClassicMapGrid
    {
        public int id;
        public string name;
        public int width;
        public int height;
        public int tileSize = 48;
        public int[] terrain;        // row-major, row 0 = top; ids index terrainNames
        public string[] terrainNames;

        public bool InBounds(int x, int y) { return x >= 0 && y >= 0 && x < width && y < height; }
        public int TerrainAt(int x, int y) { return InBounds(x, y) ? terrain[y * width + x] : -1; }

        public string TerrainName(int id)
        {
            return terrainNames != null && id >= 0 && id < terrainNames.Length ? terrainNames[id] : "";
        }
    }

    // Colour variant of a sprite triplet (set+0 red, set+1 orange, set+2 blue).
    public enum ClassicSide { Enemy = 0, Ally = 1, Player = 2 }

    public sealed class ClassicSpriteSet
    {
        public int index;
        public Sprite[] move;     // 11 x 48px
        public Sprite[] attack;   // 12 x 64px
        public Sprite[] special;  //  5 x 48px

        public Sprite[] Sheet(string sheet)
        {
            switch (sheet)
            {
                case "atk": return attack;
                case "spc": return special;
                default: return move;
            }
        }
    }

    public static class ClassicArt
    {
        public const string Root = "ClassicArt";

        // 48 px = one battle tile = one world unit. Attack frames (64 px) overhang the
        // tile by 8 px on every side, so every frame uses a centre pivot.
        public const float PixelsPerUnit = 48f;

        static ClassicManifest manifest;
        static bool manifestTried;
        static readonly Dictionary<string, ClassicAnim> anims = new Dictionary<string, ClassicAnim>();
        static readonly Dictionary<int, ClassicSpriteSet> sets = new Dictionary<int, ClassicSpriteSet>();
        static readonly Dictionary<string, Sprite> singles = new Dictionary<string, Sprite>();
        static readonly Dictionary<int, ClassicMapGrid> grids = new Dictionary<int, ClassicMapGrid>();

        public static bool Available { get { return Manifest != null; } }

        public static ClassicManifest Manifest
        {
            get
            {
                if (manifest == null && !manifestTried)
                {
                    manifestTried = true;
                    var ta = Resources.Load<TextAsset>(Root + "/classic_art");
                    if (ta == null)
                    {
                        UnityEngine.Debug.LogWarning("[ClassicArt] Resources/" + Root + "/classic_art.json not found. " +
                            "Run: python3 -I scripts/ekd5/export_art.py <your 1998 game folder>  (docs/re/CLASSIC_ART.md)");
                        return null;
                    }
                    manifest = JsonUtility.FromJson<ClassicManifest>(ta.text);
                    anims.Clear();
                    if (manifest.anims != null)
                        foreach (var a in manifest.anims) anims[a.name] = a;
                }
                return manifest;
            }
        }

        // Drops every cached sprite and re-reads the manifest (after re-exporting).
        public static void Reload()
        {
            manifest = null;
            manifestTried = false;
            anims.Clear();
            sets.Clear();
            singles.Clear();
            grids.Clear();
        }

        public static ClassicAnim Anim(string name)
        {
            if (Manifest == null) return null;
            ClassicAnim a;
            return anims.TryGetValue(name, out a) ? a : null;
        }

        // Sprite-set index for a class (jobs.csv id), side and optional officer override.
        public static int SetIndexFor(int job, ClassicSide side, int officer = -1)
        {
            var m = Manifest;
            if (m == null) return -1;
            ClassicSpriteRef r = null;
            if (officer >= 0 && m.officers != null)
                r = Array.Find(m.officers, o => o.officer == officer);
            if (r == null && m.jobs != null)
                r = Array.Find(m.jobs, j => j.job == job);
            if (r == null) return -1;
            int index = r.colors >= 3 ? r.set + (int)side : r.set;
            return index < m.unitSets ? index : -1;
        }

        public static ClassicSpriteSet SpriteSetFor(int job, ClassicSide side, int officer = -1)
        {
            int index = SetIndexFor(job, side, officer);
            return index >= 0 ? SpriteSet(index) : null;
        }

        public static ClassicSpriteSet SpriteSet(int index)
        {
            var m = Manifest;
            if (m == null || index < 0 || index >= m.unitSets) return null;
            ClassicSpriteSet s;
            if (sets.TryGetValue(index, out s)) return s;
            string b = Root + "/units/unit_" + index.ToString("000");
            s = new ClassicSpriteSet
            {
                index = index,
                move = Slice(b + "_mov", m.moveFrame, m.moveFrames),
                attack = Slice(b + "_atk", m.attackFrame, m.attackFrames),
                special = Slice(b + "_spc", m.specialFrame, m.specialFrames),
            };
            if (s.move == null) return null;
            sets[index] = s;
            return s;
        }

        public static Sprite Face(int id) { return Single("faces/face_" + id.ToString("000"), 0.5f, 0.5f); }
        public static Sprite ItemIcon(int id) { return Single("items/item_" + id.ToString("000"), 0.5f, 0.5f); }
        public static Sprite Background(int id) { return Single("backgrounds/bg_" + id.ToString("000"), 0.5f, 0.5f); }

        // Whole battle map as one sprite, pivot top-left: tile (x, y) covers
        // world [x, x+1] x [-y-1, -y] relative to the map's transform.
        public static Sprite MapSprite(int id) { return Single("maps/map_" + id.ToString("00"), 0f, 1f); }

        public static ClassicMapGrid MapGrid(int id)
        {
            ClassicMapGrid g;
            if (grids.TryGetValue(id, out g)) return g;
            var ta = Resources.Load<TextAsset>(Root + "/maps/map_" + id.ToString("00") + "_grid");
            if (ta == null) return null;
            g = JsonUtility.FromJson<ClassicMapGrid>(ta.text);
            grids[id] = g;
            return g;
        }

        public static AudioClip Sfx(string name)
        {
            return Resources.Load<AudioClip>(Root + "/sfx/" + name.ToUpperInvariant());
        }

        static Sprite Single(string path, float px, float py)
        {
            Sprite s;
            if (singles.TryGetValue(path, out s)) return s;
            var tex = Resources.Load<Texture2D>(Root + "/" + path);
            if (tex == null) return null;
            tex.filterMode = FilterMode.Point;
            s = Sprite.Create(tex, new Rect(0, 0, tex.width, tex.height), new Vector2(px, py), PixelsPerUnit,
                              0, SpriteMeshType.FullRect);
            s.name = path;
            singles[path] = s;
            return s;
        }

        // Frames are laid out left to right in one row.
        static Sprite[] Slice(string path, int size, int count)
        {
            var tex = Resources.Load<Texture2D>(path);
            if (tex == null) return null;
            tex.filterMode = FilterMode.Point;
            count = Mathf.Min(count, tex.width / size);
            var frames = new Sprite[count];
            for (int i = 0; i < count; i++)
            {
                frames[i] = Sprite.Create(tex, new Rect(i * size, tex.height - size, size, size),
                                          new Vector2(0.5f, 0.5f), PixelsPerUnit, 0, SpriteMeshType.FullRect);
                frames[i].name = tex.name + "_" + i;
            }
            return frames;
        }
    }
}
