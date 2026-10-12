using UnityEngine;

public class TileStatusMarker : MonoBehaviour
{
	private SpriteRenderer m_cachedSpriteRenderer;

	private Collider2D m_cachedCollider;

	private EnumData.TileStatus m_tileStatus;

	private SpriteRenderer m_spriteRenderer
	{
		get
		{
			return null;
		}
	}

	private Collider2D m_collider
	{
		get
		{
			return null;
		}
	}

	public EnumData.TileStatus status
	{
		get
		{
			return EnumData.TileStatus.None;
		}
	}

	private bool IsSamePosition(Transform r, Transform l)
	{
		return false;
	}

	public void MarkAsMovable(GameObject unit)
	{
	}

	public void MarkAsAttackable(GameObject unit)
	{
	}

	public void MarkAsArtificial(bool isMarkEnabled)
	{
	}

	public void MarkAsAvailableToUseItem()
	{
	}

	public void MarkAsAttackRange()
	{
	}

	public void MarkAsNoAttackRange()
	{
	}

	public void MarkAsPincerable()
	{
	}

	public void MarkAsMultiTarget(EnumData.MultiTileStatus multiTileStatus)
	{
	}

	private void Mark(Sprite sprite, bool isMarkEnabled)
	{
	}

	public void Unmark()
	{
	}
}
