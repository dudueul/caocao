using System.Collections.Generic;
using UnityEngine;

public class PopupGuildWarHelper : CaocaoUIModalPopup
{
	[SerializeField]
	private CaocaoTabButtons tabButtons;

	[SerializeField]
	private GameObject _helpObejct;

	[SerializeField]
	private GameObject _corpsInfo;

	[SerializeField]
	private UITable _helpTable;

	[SerializeField]
	private UIScrollView _helpRuleScrollView;

	[SerializeField]
	private GameObject _declareRuleObject;

	[SerializeField]
	private UITable _declareRuleTable;

	[SerializeField]
	private UITable _declareRuleSubTable;

	[SerializeField]
	private GameObject _siegeBattleObject;

	[SerializeField]
	private UITable _siegeBattleTable;

	[SerializeField]
	private UITable _siegeBattleSubTable1;

	[SerializeField]
	private UITable _siegeBattleSubTable2;

	[SerializeField]
	private UITable _siegeBattleSubTable3;

	[SerializeField]
	private UITable _siegeBattleSubTable4;

	[SerializeField]
	private GameObject _rewardObject;

	[SerializeField]
	private UITable _rewardTable;

	[SerializeField]
	private UITable _rewardSubTable1;

	[SerializeField]
	private UIGrid _corpsTypeTable;

	[SerializeField]
	private UIScrollView _corpsTypeScrollView;

	[SerializeField]
	private UILabelLocalized _corpsJobDesc;

	[SerializeField]
	private GameObject _hitRangeTexture;

	[SerializeField]
	private GameObject _effectRangeTexture;

	[SerializeField]
	private Transform _grade;

	[SerializeField]
	private UIReuseGrid _corpsPassiveList;

	[SerializeField]
	private GameObject _corpsPortrate;

	[SerializeField]
	private UIReuseGrid _skillReuseGrid;

	[SerializeField]
	private UILabelLocalized _skillName;

	[SerializeField]
	private UILabelLocalized _skillDesc;

	private static PopupGuildWarHelper _self;

	private GameObject _lastSelectedCorpsJobObj;

	private int _selectedJobId;

	private Dictionary<int, bool> _corpsJobTypeShowCondition;

	private Dictionary<int, List<GameObject>> _corpsJobTypeDic;

	private List<int> _functionList;

	protected override void ClosePanelImpl()
	{
	}

	protected override void ShowPanelImpl()
	{
	}

	private void OnEnable()
	{
	}

	private void OnDisable()
	{
	}

	public override void OnFocusOn()
	{
	}

	public override void OnFocusOut()
	{
	}

	public override void OnTappingOutSide()
	{
	}

	private static void checkSelf()
	{
	}

	public static void Show()
	{
	}

	private void initData()
	{
	}

	private void RefreshCorpsFunctionData(int functionId)
	{
	}

	private void initCorpsFuntion()
	{
	}

	private void RefreshCorpsData(GuildWarCorpsJob jobData)
	{
	}

	private void OnTabChanged(CaocaoButton button)
	{
	}

	public void OnClickClosed()
	{
	}
}
