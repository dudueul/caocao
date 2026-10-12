using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using NPA;

public class CaocaoToyPushManager : Singleton<CaocaoToyPushManager>
{
	private Queue<CaocaoToyPushData> pushDatas;

	public void Init()
	{
	}

	public void SendLocalPush(int id, string message, string meta, NPNotificationTime time, int pushType)
	{
	}

	public void OnReceivePush(CaocaoToyPushData pushData)
	{
	}

	[DebuggerHidden]
	private IEnumerator NoticePush(CaocaoToyPushData data)
	{
		return null;
	}

	private void Update()
	{
	}

	private void AddLocalPushHandler()
	{
	}

	private void AddHarvestFullLocalPushHandler()
	{
	}

	private void AddFreeTreasureBoxLocalPushHandler()
	{
	}

	private void AddInstanceExpeditionPushHandler()
	{
	}

	private void AddWorldBossRewardEndPushHandler()
	{
	}
}
