using System;
using System.Collections;
using System.Diagnostics;
using UnityEngine;

public class StageNeedUnitListItem : MonoBehaviour
{
	[SerializeField]
	private UIButton btnGoUnitTree;

	[SerializeField]
	private PrefabInstance unitUIInstance;

	[SerializeField]
	private UILabelLocalized unitName;

	[SerializeField]
	private UILabelLocalized unitTreeName;

	[SerializeField]
	private Transform selectedSprite;

	[SerializeField]
	private UISprite buttonSprite;

	[SerializeField]
	private UISprite buttonColor;

	[SerializeField]
	private UILabelLocalized buttonLabel;

	private int unitTreeId;

	private int unitTreeNodeId;

	public void UpdateUI(int unitId, Action goUnitTreeClickCallback)
	{
	}

	[DebuggerHidden]
	private IEnumerator OpenProperUnitTree(Action callback)
	{
		return null;
	}
}
