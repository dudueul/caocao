using System;
using UnityEngine;

public abstract class UIRect : MonoBehaviour
{
	[Serializable]
	public class AnchorPoint
	{
		public Transform target;

		public float relative;

		public int absolute;

		[NonSerialized]
		public UIRect rect;

		[NonSerialized]
		public Camera targetCam;

		public AnchorPoint()
		{
		}

		public AnchorPoint(float relative)
		{
		}

		public void Set(float relative, float absolute)
		{
		}

		public void Set(Transform target, float relative, float absolute)
		{
		}

		public void SetToNearest(float abs0, float abs1, float abs2)
		{
		}

		public void SetToNearest(float rel0, float rel1, float rel2, float abs0, float abs1, float abs2)
		{
		}

		public void SetHorizontal(Transform parent, float localPos)
		{
		}

		public void SetVertical(Transform parent, float localPos)
		{
		}

		public Vector3[] GetSides(Transform relativeTo)
		{
			return null;
		}
	}

	public enum AnchorUpdate
	{
		OnEnable = 0,
		OnUpdate = 1,
		OnStart = 2
	}

	public AnchorPoint leftAnchor;

	public AnchorPoint rightAnchor;

	public AnchorPoint bottomAnchor;

	public AnchorPoint topAnchor;

	public AnchorUpdate updateAnchors;

	protected GameObject mGo;

	protected Transform mTrans;

	protected BetterList<UIRect> mChildren;

	protected bool mChanged;

	protected bool mStarted;

	protected bool mParentFound;

	[NonSerialized]
	private bool mUpdateAnchors;

	[NonSerialized]
	private int mUpdateFrame;

	[NonSerialized]
	private bool mAnchorsCached;

	[NonSerialized]
	private UIRoot mRoot;

	[NonSerialized]
	private UIRect mParent;

	[NonSerialized]
	private bool mRootSet;

	[NonSerialized]
	protected Camera mCam;

	[NonSerialized]
	public float finalAlpha;

	protected static Vector3[] mSides;

	public GameObject cachedGameObject
	{
		get
		{
			return null;
		}
	}

	public Transform cachedTransform
	{
		get
		{
			return null;
		}
	}

	public Camera anchorCamera
	{
		get
		{
			return null;
		}
	}

	public bool isFullyAnchored
	{
		get
		{
			return false;
		}
	}

	public virtual bool isAnchoredHorizontally
	{
		get
		{
			return false;
		}
	}

	public virtual bool isAnchoredVertically
	{
		get
		{
			return false;
		}
	}

	public virtual bool canBeAnchored
	{
		get
		{
			return false;
		}
	}

	public UIRect parent
	{
		get
		{
			return null;
		}
	}

	public UIRoot root
	{
		get
		{
			return null;
		}
	}

	public bool isAnchored
	{
		get
		{
			return false;
		}
	}

	public abstract float alpha { get; set; }

	public abstract Vector3[] localCorners { get; }

	public abstract Vector3[] worldCorners { get; }

	protected float cameraRayDistance
	{
		get
		{
			return 0f;
		}
	}

	public abstract float CalculateFinalAlpha(int frameID);

	public virtual void Invalidate(bool includeChildren)
	{
	}

	public virtual Vector3[] GetSides(Transform relativeTo)
	{
		return null;
	}

	protected Vector3 GetLocalPos(AnchorPoint ac, Transform trans)
	{
		return default(Vector3);
	}

	protected virtual void OnEnable()
	{
	}

	protected virtual void OnInit()
	{
	}

	protected virtual void OnDisable()
	{
	}

	protected void Start()
	{
	}

	public void Update()
	{
	}

	public void UpdateAnchors()
	{
	}

	protected abstract void OnAnchor();

	public void SetAnchor(Transform t)
	{
	}

	public void SetAnchor(GameObject go)
	{
	}

	public void SetAnchor(GameObject go, int left, int bottom, int right, int top)
	{
	}

	public void ResetAnchors()
	{
	}

	public void ResetAndUpdateAnchors()
	{
	}

	public abstract void SetRect(float x, float y, float width, float height);

	private void FindCameraFor(AnchorPoint ap)
	{
	}

	public virtual void ParentHasChanged()
	{
	}

	protected abstract void OnStart();

	protected virtual void OnUpdate()
	{
	}
}
