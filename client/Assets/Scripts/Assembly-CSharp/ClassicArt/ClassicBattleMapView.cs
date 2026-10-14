using UnityEngine;

namespace Rebuild.Classic
{
    // One 1998 battle map: the painted picture plus its terrain grid.
    // The transform sits on the map's top-left corner; tile (0,0) is top-left,
    // x grows right and y grows down, one tile = one world unit.
    [RequireComponent(typeof(SpriteRenderer))]
    public class ClassicBattleMapView : MonoBehaviour
    {
        public int mapId;
        public bool loadOnStart = true;
        public bool drawGridGizmos = true;

        public ClassicMapGrid Grid { get; private set; }
        public int Width { get { return Grid != null ? Grid.width : 0; } }
        public int Height { get { return Grid != null ? Grid.height : 0; } }

        SpriteRenderer sr;

        void Awake()
        {
            sr = GetComponent<SpriteRenderer>();
            sr.sortingOrder = -100;
        }

        void Start()
        {
            if (loadOnStart && Grid == null) Load(mapId);
        }

        public bool Load(int id)
        {
            if (sr == null) Awake();
            var grid = ClassicArt.MapGrid(id);
            var sprite = ClassicArt.MapSprite(id);
            if (grid == null || sprite == null)
            {
                UnityEngine.Debug.LogWarning("[ClassicBattleMapView] map " + id + " not exported");
                return false;
            }
            mapId = id;
            Grid = grid;
            sr.sprite = sprite;
            name = "Map " + id.ToString("00") + " " + grid.name;
            return true;
        }

        public Vector3 TileToWorld(int x, int y)
        {
            return transform.TransformPoint(new Vector3(x + 0.5f, -(y + 0.5f), 0f));
        }

        public bool WorldToTile(Vector3 world, out int x, out int y)
        {
            Vector3 p = transform.InverseTransformPoint(world);
            x = Mathf.FloorToInt(p.x);
            y = Mathf.FloorToInt(-p.y);
            return Grid != null && Grid.InBounds(x, y);
        }

        public int TerrainAt(int x, int y) { return Grid != null ? Grid.TerrainAt(x, y) : -1; }
        public string TerrainNameAt(int x, int y) { return Grid != null ? Grid.TerrainName(TerrainAt(x, y)) : ""; }

        public Bounds WorldBounds
        {
            get
            {
                var c = transform.TransformPoint(new Vector3(Width * 0.5f, -Height * 0.5f, 0f));
                return new Bounds(c, new Vector3(Width, Height, 0f));
            }
        }

        void OnDrawGizmosSelected()
        {
            if (!drawGridGizmos || Grid == null) return;
            Gizmos.color = new Color(1f, 1f, 1f, 0.25f);
            for (int x = 0; x <= Width; x++)
                Gizmos.DrawLine(transform.TransformPoint(new Vector3(x, 0, 0)), transform.TransformPoint(new Vector3(x, -Height, 0)));
            for (int y = 0; y <= Height; y++)
                Gizmos.DrawLine(transform.TransformPoint(new Vector3(0, -y, 0)), transform.TransformPoint(new Vector3(Width, -y, 0)));
        }
    }
}
