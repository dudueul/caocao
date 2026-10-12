using UnityEngine;

public class UIGuildWarMinimapCameraArea : UIWidget
{
	private Vector3[] vertices;

	private Vector2 minimapAreaMin;

	private Vector2 minimapAreaMax;

	private Material m_material;

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

	protected override void OnStart()
	{
	}

	private void OnRender(Material mat)
	{
	}

	public override void OnFill(BetterList<Vector3> verts, BetterList<Vector2> uvs, BetterList<Color32> cols)
	{
	}

	protected override void OnAnchor()
	{
	}

	public void SetMinimapArea(Vector2 min, Vector2 max)
	{
	}

	private void UpdateCameraArea()
	{
	}

	protected override void OnUpdate()
	{
	}
}
