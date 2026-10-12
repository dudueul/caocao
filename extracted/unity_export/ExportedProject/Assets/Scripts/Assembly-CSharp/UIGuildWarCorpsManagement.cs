using GuildWar;
using LitJson;
using UnityEngine;

public class UIGuildWarCorpsManagement : MonoBehaviour
{
	private enum UIStatus
	{
		None = 0,
		EntryCost = 1,
		CorpsCreate = 2,
		CorpsInfo = 3,
		Respawn = 4,
		MountingSiegeWeapon = 5
	}

	[SerializeField]
	private GameObject _faceDim;

	[SerializeField]
	private UITexture _faceTexture;

	[SerializeField]
	private GameObject _entryCost;

	[SerializeField]
	private UILabel _foodNeedValue;

	[SerializeField]
	private UILabel _silverNeedValue;

	[SerializeField]
	private GameObject _corpsCreate;

	[SerializeField]
	private UILabel _corpsPossibleCreateCount;

	[SerializeField]
	private UILabelLocalized _corpsCreateTitleLabel;

	[SerializeField]
	private GameObject _btnCorpsCreate;

	[SerializeField]
	private UILabelLocalized _corpsCreateLabel;

	[SerializeField]
	private GameObject _btnCorpsPerchase;

	[SerializeField]
	private GameObject _corpsInfo;

	[SerializeField]
	private UILabel _corpsLv;

	[SerializeField]
	private UILabelLocalized _corpsJob;

	[SerializeField]
	private UISlider _corpsHpSlider;

	[SerializeField]
	private UISprite _corpsHPBar;

	[SerializeField]
	private UISprite _corpsDurabilityBar;

	[SerializeField]
	private UILabel _corpsHpText;

	[SerializeField]
	private UISlider _corpsMpSlider;

	[SerializeField]
	private UILabel _corpsMpText;

	[SerializeField]
	private UISlider _corpsExpSlider;

	[SerializeField]
	private UILabel _corpsExpText;

	[SerializeField]
	private GameObject _timer;

	[SerializeField]
	private UILabelLocalized _timerDesc;

	[SerializeField]
	private UILabel _timerSec;

	[SerializeField]
	private UILabel _timerMilliSec;

	private bool isInit;

	private GuildWar.GuildData guildData;

	private GuildWar.UserData userData;

	private UserCorpsData userCorpsData;

	private CorpsData corpsData;

	private bool _isNoEntry;

	private int seasonKey;

	private int seasonDetailKey;

	private int p_guildwar_town_id;

	private int p_battleworld_id;

	private UIStatus currentUIStatus;

	private int dismissCustomActionKey;

	private UserKey myUserKey;

	private UserCorpsKey myUserCorpsKey;

	private int _maxCoolTime;

	private int maxCoolTime
	{
		get
		{
			return 0;
		}
	}

	private void OnDestroy()
	{
	}

	public void init(GuildWar.GuildData guildData, int seasonKey, int seasonDetailKey, int p_battleworld_id, int p_guildwar_town_id)
	{
	}

	private void initGuildWarData()
	{
	}

	private void UpdateTable()
	{
	}

	private void OnRefCorpsUpdated()
	{
	}

	private void OnCorpsUpdated(CorpsData corpsData)
	{
	}

	private void OnUserCorpsAdded(UserCorpsKey userCorpsKey, UserCorpsData userCorpsData)
	{
	}

	private void OnUserAdded(UserKey userKey, GuildWar.UserData userData)
	{
	}

	private void UpdateUI()
	{
	}

	private void Update()
	{
	}

	private string GetNeedGoodsValue(int nextCount)
	{
		return null;
	}

	private string GetCreateText(bool isTrue)
	{
		return null;
	}

	private string GetCreateTitleText(bool isFree)
	{
		return null;
	}

	public void UpdateText()
	{
	}

	private void OnBattleWorldJoin(JsonData data)
	{
	}

	private bool CommonCheck(JsonData response)
	{
		return false;
	}

	private void createUserCallBack()
	{
	}

	public void OnClickCameraMove()
	{
	}

	public void OnClickBattleWorldJoin()
	{
	}

	public void OnClickFreeCreateCorps()
	{
	}

	public void OnClickChargeCreateCorps()
	{
	}

	public void OnClickDismiss()
	{
	}
}
