using System;
using System.Collections.Generic;
using UnityEngine;

[ExecuteInEditMode]
public class UIPanel : UIRect
{
	public enum RenderQueue
	{
		Automatic = 0,
		StartAt = 1,
		Explicit = 2
	}

	public delegate void OnGeometryUpdated();

	public delegate void OnClippingMoved(UIPanel panel);

	public static List<UIPanel> list;

	public OnGeometryUpdated onGeometryUpdated;

	public bool showInPanelTool;

	public bool generateNormals;

	public bool widgetsAreStatic;

	public bool cullWhileDragging;

	public bool alwaysOnScreen;

	public bool anchorOffset;

	public bool softBorderPadding;

	public RenderQueue renderQueue;

	public int startingRenderQueue;

	[NonSerialized]
	public List<UIWidget> widgets;

	[NonSerialized]
	public List<UIDrawCall> drawCalls;

	[NonSerialized]
	public Matrix4x4 worldToLocal;

	[NonSerialized]
	public Vector4 drawCallClipRange;

	public OnClippingMoved onClipMove;

	[HideInInspector]
	[SerializeField]
	private Texture2D mClipTexture;

	[HideInInspector]
	[SerializeField]
	private float mAlpha;

	[HideInInspector]
	[SerializeField]
	private UIDrawCall.Clipping mClipping;

	[HideInInspector]
	[SerializeField]
	private Vector4 mClipRange;

	[HideInInspector]
	[SerializeField]
	private Vector2 mClipSoftness;

	[HideInInspector]
	[SerializeField]
	private int mDepth;

	[HideInInspector]
	[SerializeField]
	private int mSortingOrder;

	private bool mRebuild;

	private bool mResized;

	[SerializeField]
	private Vector2 mClipOffset;

	private float mCullTime;

	private float mUpdateTime;

	private int mMatrixFrame;

	private int mAlphaFrameID;

	private int mLayer;

	private static float[] mTemp;

	private Vector2 mMin;

	private Vector2 mMax;

	private bool mHalfPixelOffset;

	private bool mSortWidgets;

	private bool mUpdateScroll;

	private UIPanel mParentPanel;

	private static Vector3[] mCorners;

	private static int mUpdateFrame;

	private static BetterList<Vector3> ms_verts;

	private static BetterList<Vector2> ms_uvs;

	private static BetterList<Color32> ms_cols;

	private static BetterList<Vector3> ms_norms;

	private static BetterList<Vector4> ms_tans;

	private UIDrawCall.OnRenderCallback mOnRender;

	private bool mForced;

	public static int nextUnusedDepth
	{
		get
		{
			return 0;
		}
	}

	public override bool canBeAnchored
	{
		get
		{
			return false;
		}
	}

	public override float alpha
	{
		get
		{
			return 0f;
		}
		set
		{
		}
	}

	public int depth
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

	public float width
	{
		get
		{
			return 0f;
		}
	}

	public float height
	{
		get
		{
			return 0f;
		}
	}

	public bool halfPixelOffset
	{
		get
		{
			return false;
		}
	}

	public bool usedForUI
	{
		get
		{
			return false;
		}
	}

	public Vector3 drawCallOffset
	{
		get
		{
			return default(Vector3);
		}
	}

	public UIDrawCall.Clipping clipping
	{
		get
		{
			return UIDrawCall.Clipping.None;
		}
		set
		{
		}
	}

	public UIPanel parentPanel
	{
		get
		{
			return null;
		}
	}

	public int clipCount
	{
		get
		{
			return 0;
		}
	}

	public bool hasClipping
	{
		get
		{
			return false;
		}
	}

	public bool hasCumulativeClipping
	{
		get
		{
			return false;
		}
	}

	[Obsolete]
	public bool clipsChildren
	{
		get
		{
			return false;
		}
	}

	public Vector2 clipOffset
	{
		get
		{
			return default(Vector2);
		}
		set
		{
		}
	}

	public Texture2D clipTexture
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	[Obsolete]
	public Vector4 clipRange
	{
		get
		{
			return default(Vector4);
		}
		set
		{
		}
	}

	public Vector4 baseClipRegion
	{
		get
		{
			return default(Vector4);
		}
		set
		{
		}
	}

	public Vector4 finalClipRegion
	{
		get
		{
			return default(Vector4);
		}
	}

	public Vector2 clipSoftness
	{
		get
		{
			return default(Vector2);
		}
		set
		{
		}
	}

	public override Vector3[] localCorners
	{
		get
		{
			return null;
		}
	}

	public override Vector3[] worldCorners
	{
		get
		{
			return null;
		}
	}

	static UIPanel()
	{
	}

	public static int CompareFunc(UIPanel a, UIPanel b)
	{
		return 0;
	}

	private void InvalidateClipping()
	{
	}

	public override Vector3[] GetSides(Transform relativeTo)
	{
		return null;
	}

	public override void Invalidate(bool includeChildren)
	{
	}

	public override float CalculateFinalAlpha(int frameID)
	{
		return 0f;
	}

	public override void SetRect(float x, float y, float width, float height)
	{
	}

	public bool IsVisible(Vector3 a, Vector3 b, Vector3 c, Vector3 d)
	{
		return false;
	}

	public bool IsVisible(Vector3 worldPos)
	{
		return false;
	}

	public bool IsVisible(UIWidget w)
	{
		return false;
	}

	public bool Affects(UIWidget w)
	{
		return false;
	}

	public void RebuildAllDrawCalls()
	{
	}

	public void SetDirty()
	{
	}

	private void Awake()
	{
	}

	private void FindParent()
	{
	}

	public override void ParentHasChanged()
	{
	}

	protected override void OnStart()
	{
	}

	protected override void OnEnable()
	{
	}

	protected override void OnInit()
	{
	}

	protected override void OnDisable()
	{
	}

	private void UpdateTransformMatrix()
	{
	}

	protected override void OnAnchor()
	{
	}

	private void LateUpdate()
	{
	}

	private void UpdateSelf()
	{
	}

	public void SortWidgets()
	{
	}

	private void CopyBuffer2Drawcall(UIDrawCall dc)
	{
	}

	private void ClearBuffer()
	{
	}

	private void FillAllDrawCalls()
	{
	}

	private bool FillDrawCall(UIDrawCall dc)
	{
		return false;
	}

	private void UpdateDrawCalls()
	{
	}

	private void UpdateLayers()
	{
	}

	private void UpdateWidgets()
	{
	}

	public UIDrawCall FindDrawCall(UIWidget w)
	{
		return null;
	}

	public void AddWidget(UIWidget w)
	{
	}

	public void RemoveWidget(UIWidget w)
	{
	}

	public void Refresh()
	{
	}

	public virtual Vector3 CalculateConstrainOffset(Vector2 min, Vector2 max)
	{
		return default(Vector3);
	}

	public bool ConstrainTargetToBounds(Transform target, ref Bounds targetBounds, bool immediate)
	{
		return false;
	}

	public bool ConstrainTargetToBounds(Transform target, bool immediate)
	{
		return false;
	}

	public static UIPanel Find(Transform trans)
	{
		return null;
	}

	public static UIPanel Find(Transform trans, bool createIfMissing)
	{
		return null;
	}

	public static UIPanel Find(Transform trans, bool createIfMissing, int layer)
	{
		return null;
	}

	public Vector2 GetWindowSize()
	{
		return default(Vector2);
	}

	public Vector2 GetViewSize()
	{
		return default(Vector2);
	}
}
