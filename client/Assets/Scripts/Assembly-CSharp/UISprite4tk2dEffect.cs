using UnityEngine;

public class UISprite4tk2dEffect : UIWidget
{
	protected tk2dSprite mSpriteTk2d;

	public override Material material
	{
		get
		{
			return null;
		}
	}

	protected override void OnInit()
	{
	}

	private void OnSpriteChanged(tk2dBaseSprite sprite)
	{
	}

	public void Reset()
	{
	}

	private void ResetShader()
	{
	}

	private void OnDestroy()
	{
	}

	public override void OnFill(BetterList<Vector3> verts, BetterList<Vector2> uvs, BetterList<Color32> cols)
	{
	}
}
