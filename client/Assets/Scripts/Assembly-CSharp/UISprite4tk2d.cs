using UnityEngine;

public class UISprite4tk2d : UIWidget
{
	protected tk2dSprite mSpriteTk2d;

	private MeshRenderer m_meshRenderer;

	private Material m_material;

	public override Material material
	{
		get
		{
			return null;
		}
	}

	public void ApplyMaterial()
	{
	}

	protected override void OnInit()
	{
	}

	private void OnSpriteChanged(tk2dBaseSprite sprite)
	{
	}

	private void OnDestroy()
	{
	}

	public override void OnFill(BetterList<Vector3> verts, BetterList<Vector2> uvs, BetterList<Color32> cols)
	{
	}
}
