using System.Collections.Generic;
using UnityEngine;

public class UIThousandRewardListItem : MonoBehaviour
{
	public GameObject nowGo;

	public UILabelLocalized stageLabel;

	public UILabelLocalized tileInfoLabel;

	public UILabelLocalized levelLabel;

	public UIGrid reinforceGrid;

	public List<GameObject> reinforceGoes;

	public UILabelLocalized rewardGoodsCountLabel;

	public UILabelLocalized rewardItemCountLabel;

	public GameObject checkBossStageGo;

	public void UpdateUi(ThousandBattle thousandBattle)
	{
	}
}
