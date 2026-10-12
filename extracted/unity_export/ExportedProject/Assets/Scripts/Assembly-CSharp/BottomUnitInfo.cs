public class BottomUnitInfo : SelfRegister2UIComponentsMonobehaviour
{
	public UILabelLocalized m_name;

	public UILabelLocalized m_job;

	public UILabelLocalized m_level;

	public PrefabInstance m_unitInst;

	public PrefabInstance m_unitStatInst;

	public PrefabInstance m_jobStyleInst;

	public PrefabInstance m_jobLevelInst;

	public UILabelLocalized m_tileAttributeText;

	public UILabelLocalized m_tileAttributePer;

	public UIGrid passiveGrid;

	public UIGrid conditionGrid;

	public UIProgressBar m_exp;

	private const string passiveIconSmallPoolName = "passiveIconSmall";

	private const string conditionIconSmallPoolName = "conditionIconSmall";

	private bool m_started;

	private BottomWeaknessInfo m_bottomWeaknessInfo;

	private void Start()
	{
	}

	public override void OnInit()
	{
	}

	private void OnDisable()
	{
	}

	private void RemoveAllGridItem()
	{
	}

	private void UpdateUI()
	{
	}

	private void OnStatusChangedHandler(ControlFlow.statusType status)
	{
	}
}
