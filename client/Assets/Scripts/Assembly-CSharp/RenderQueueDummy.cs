using UnityEngine;

public class RenderQueueDummy : UIWidget
{
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

	public override void OnFill(BetterList<Vector3> verts, BetterList<Vector2> uvs, BetterList<Color32> cols)
	{
	}

	protected override void OnStart()
	{
	}

	protected override void OnEnable()
	{
	}

	protected override void OnDisable()
	{
	}

	private void OnRender(Material mat)
	{
	}
}
