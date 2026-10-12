using UnityEngine;

[ExecuteInEditMode]
public class tk2dSpriteFromTexture : MonoBehaviour
{
	public Texture texture;

	public tk2dSpriteCollectionSize spriteCollectionSize;

	public tk2dBaseSprite.Anchor anchor;

	private tk2dSpriteCollectionData spriteCollection;

	private tk2dBaseSprite _sprite;

	private tk2dBaseSprite Sprite
	{
		get
		{
			return null;
		}
	}

	public bool HasSpriteCollection
	{
		get
		{
			return false;
		}
	}

	private void Awake()
	{
	}

	private void OnDestroy()
	{
	}

	public void Create(tk2dSpriteCollectionSize spriteCollectionSize, Texture texture, tk2dBaseSprite.Anchor anchor)
	{
	}

	public void Clear()
	{
	}

	public void ForceBuild()
	{
	}

	private void DestroyInternal()
	{
	}
}
