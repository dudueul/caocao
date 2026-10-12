using UnityEngine;

public class DuelIntroSceneController : MonoBehaviour
{
	private GameObject m_ownUnit;

	private GameObject m_enemyUnit;

	private int m_ownKingdom;

	private int m_enemyKingdom;

	private int m_introMapId;

	public UI2DSprite introBack;

	public UITexture face1;

	public UILabelLocalized forces1;

	public UILabelLocalized name1;

	public UILabelLocalized forceInt1;

	public UnitDataForRenderer unit1Data;

	public UnitAnimationBase unit1Ani;

	public tk2dSprite unit1Sprite;

	public UITexture face2;

	public UILabelLocalized forces2;

	public UILabelLocalized name2;

	public UILabelLocalized forceInt2;

	public UnitDataForRenderer unit2Data;

	public UnitAnimationBase unit2Ani;

	public tk2dSprite unit2Sprite;

	public GameObject ownUnit
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int ownKingdom
	{
		set
		{
		}
	}

	public GameObject enemyUnit
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int enemyKingdom
	{
		set
		{
		}
	}

	private UnitDataForRenderer ownUnitData
	{
		get
		{
			return null;
		}
	}

	private UnitDataForRenderer enemyUnitData
	{
		get
		{
			return null;
		}
	}

	public int introMapId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	private void Start()
	{
	}

	private void UpdateIntroImage()
	{
	}

	private void UpdateEnemyUnitInformation()
	{
	}

	private void UpdateOwnUnitInformation()
	{
	}

	public void UpdateUnitSprite()
	{
	}

	private void UpdateOwnSprite()
	{
	}

	private void UpdateEnemySprite()
	{
	}

	private string GetKingdomString(int kingdom)
	{
		return null;
	}
}
