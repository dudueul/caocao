using UnityEngine;

public class UIGeometry
{
	public BetterList<Vector3> verts;

	public BetterList<Vector2> uvs;

	public BetterList<Color32> cols;

	private BetterList<Vector3> mRtpVerts;

	private Vector3 mRtpNormal;

	private Vector4 mRtpTan;

	private static BetterList<Vector3> ms_RtpVerts;

	public bool hasVertices
	{
		get
		{
			return false;
		}
	}

	public bool hasTransformed
	{
		get
		{
			return false;
		}
	}

	static UIGeometry()
	{
	}

	public void Clear()
	{
	}

	private void CopyBuffer2RtpVerts()
	{
	}

	private void ClearBuffer()
	{
	}

	public void ApplyTransform(Matrix4x4 widgetToPanel)
	{
	}

	public void WriteToBuffers(BetterList<Vector3> v, BetterList<Vector2> u, BetterList<Color32> c, BetterList<Vector3> n, BetterList<Vector4> t)
	{
	}
}
