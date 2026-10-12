using System;
using System.Collections.Generic;
using UnityEngine;

[ExecuteInEditMode]
public class UIDrawCall : MonoBehaviour
{
	public enum Clipping
	{
		None = 0,
		TextureMask = 1,
		SoftClip = 3,
		ConstrainButDontClip = 4
	}

	public delegate void OnRenderCallback(Material mat);

	private static BetterList<UIDrawCall> mActiveList;

	private static BetterList<UIDrawCall> mInactiveList;

	private static int[] NguiIndicesOrder;

	private static int[] Tk2dIndicesOrder;

	[NonSerialized]
	[HideInInspector]
	public UIWidget.IndicesFormat mIndicesFormat;

	public UIWidget.IndicesFormat mCachedIndicesFormat;

	[NonSerialized]
	[HideInInspector]
	public int widgetCount;

	[NonSerialized]
	[HideInInspector]
	public int depthStart;

	[NonSerialized]
	[HideInInspector]
	public int depthEnd;

	[NonSerialized]
	[HideInInspector]
	public UIPanel manager;

	[NonSerialized]
	[HideInInspector]
	public UIPanel panel;

	[NonSerialized]
	[HideInInspector]
	public Texture2D clipTexture;

	[NonSerialized]
	[HideInInspector]
	public bool alwaysOnScreen;

	[NonSerialized]
	[HideInInspector]
	public BetterList<Vector3> verts;

	[NonSerialized]
	[HideInInspector]
	public BetterList<Vector3> norms;

	[NonSerialized]
	[HideInInspector]
	public BetterList<Vector4> tans;

	[NonSerialized]
	[HideInInspector]
	public BetterList<Vector2> uvs;

	[NonSerialized]
	[HideInInspector]
	public BetterList<Color32> cols;

	private Material mMaterial;

	private Texture mTexture;

	private Shader mShader;

	private int mClipCount;

	private Transform mTrans;

	private Mesh mMesh;

	private MeshFilter mFilter;

	private MeshRenderer mRenderer;

	private Material mDynamicMat;

	private int[] mIndices;

	protected MaterialPropertyBlock mPropertyBlock;

	private bool mRebuildMat;

	private bool mLegacyShader;

	private int mRenderQueue;

	private int mTriangles;

	[NonSerialized]
	public bool isDirty;

	[NonSerialized]
	private bool mTextureClip;

	public OnRenderCallback onRender;

	private const int maxIndexBufferCache = 10;

	private static List<int[]> mCacheTk2dIndices;

	private static List<int[]> mCacheNguiIndices;

	private static int[] ClipRange;

	private static int[] ClipArgs;

	[Obsolete]
	public static BetterList<UIDrawCall> list
	{
		get
		{
			return null;
		}
	}

	public static BetterList<UIDrawCall> activeList
	{
		get
		{
			return null;
		}
	}

	public static BetterList<UIDrawCall> inactiveList
	{
		get
		{
			return null;
		}
	}

	public int renderQueue
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int sortingOrder
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int finalRenderQueue
	{
		get
		{
			return 0;
		}
	}

	public Transform cachedTransform
	{
		get
		{
			return null;
		}
	}

	public Material baseMaterial
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public Material dynamicMaterial
	{
		get
		{
			return null;
		}
	}

	public Texture mainTexture
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public Shader shader
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int triangles
	{
		get
		{
			return 0;
		}
	}

	public bool isClipped
	{
		get
		{
			return false;
		}
	}

	private void CreateMaterial()
	{
	}

	private Material RebuildMaterial()
	{
		return null;
	}

	private void UpdateMaterials()
	{
	}

	public void UpdateGeometry(int widgetCount)
	{
	}

	private int[] GenerateCachedIndexBuffer(int vertexCount, int indexCount)
	{
		return null;
	}

	private void OnWillRenderObject()
	{
	}

	private static void CalcClipMagicNumber(UIPanel currentPanel, ref float h, ref float v)
	{
	}

	private void SetClipping(int index, Vector4 cr, Vector2 soft, float angle, float h, float v)
	{
	}

	private void Awake()
	{
	}

	private void OnEnable()
	{
	}

	private void OnDisable()
	{
	}

	private void OnDestroy()
	{
	}

	public static UIDrawCall Create(UIPanel panel, Material mat, Texture tex, Shader shader, Renderer _renderer)
	{
		return null;
	}

	private static UIDrawCall Create(string name, UIPanel pan, Material mat, Texture tex, Shader shader, Renderer _renderer)
	{
		return null;
	}

	private static UIDrawCall Create(string name)
	{
		return null;
	}

	public static void ClearAll()
	{
	}

	public static void ReleaseAll()
	{
	}

	public static void ReleaseInactive()
	{
	}

	public static int Count(UIPanel panel)
	{
		return 0;
	}

	public static void Destroy(UIDrawCall dc)
	{
	}
}
