using System;
using System.Collections.Generic;
using UnityEngine;

[ExecuteInEditMode]
public class UITexture : UIBasicSprite, IWeakResourceRef
{
	[HideInInspector]
	[SerializeField]
	private Rect mRect;

	[HideInInspector]
	[SerializeField]
	private Texture mTexture;

	[HideInInspector]
	[SerializeField]
	private Material mMat;

	[HideInInspector]
	[SerializeField]
	private Shader mShader;

	[HideInInspector]
	[SerializeField]
	private Vector4 mBorder;

	[HideInInspector]
	[SerializeField]
	private bool mFixedAspect;

	[NonSerialized]
	private int mPMA;

	[SerializeField]
	public string mTexturePath;

	[SerializeField]
	public string mMatPath;

	public override Texture mainTexture
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public override Material material
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public override Shader shader
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public override bool premultipliedAlpha
	{
		get
		{
			return false;
		}
	}

	public override Vector4 border
	{
		get
		{
			return default(Vector4);
		}
		set
		{
		}
	}

	public Rect uvRect
	{
		get
		{
			return default(Rect);
		}
		set
		{
		}
	}

	public override Vector4 drawingDimensions
	{
		get
		{
			return default(Vector4);
		}
	}

	public bool fixedAspect
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public override void MakePixelPerfect()
	{
	}

	protected override void OnUpdate()
	{
	}

	public override void OnFill(BetterList<Vector3> verts, BetterList<Vector2> uvs, BetterList<Color32> cols)
	{
	}

	protected override void OnInit()
	{
	}

	public void DisconnectResourceRef(List<string> adjustedPaths)
	{
	}

	void IWeakResourceRef.ConnectResourceRef()
	{
	}
}
