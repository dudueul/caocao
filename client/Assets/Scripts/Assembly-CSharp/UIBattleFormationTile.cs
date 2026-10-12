using Caocao;
using UnityEngine;

public class UIBattleFormationTile : MonoBehaviour
{
	public GameObject frontLine;

	public GameObject centerLine;

	public GameObject backLine;

	public GameObject grayTile;

	public GameObject greenTile;

	public GameObject bigNumberGo;

	public GameObject smallNumberGo;

	public UITexture uiFaceTexture;

	private UILabelLocalized m_numberLabel;

	private void OnDestroy()
	{
	}

	public void UseBigNumber(bool use)
	{
	}

	public void SetPositionType(EnumData.PvpPositionType pvpPositionType)
	{
	}

	public void SetIndex(int index, bool useBigNumber)
	{
	}

	public void SetFace(Texture faceTexture)
	{
	}

	public void SetBrawlFight(UnitSide unitSide)
	{
	}

	public void SetEmpty()
	{
	}
}
