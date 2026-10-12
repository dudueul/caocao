using Caocao;
using UnityEngine;

public class UnitShadow : MonoBehaviour
{
	public tk2dSprite m_shadowSprite;

	private UnitAnimation m_unitAnimation;

	private UnitDataForRenderer m_unitData;

	private UnitDirection unitDirection
	{
		get
		{
			return UnitDirection.back;
		}
	}

	private void OnEnable()
	{
	}

	private void Start()
	{
	}

	private void OnUnitDirectionChanged(UnitAnimationBase unitAnimationBase, UnitDirection direction)
	{
	}

	private void ScaleShdaowByDirection()
	{
	}

	private void OnDisable()
	{
	}
}
