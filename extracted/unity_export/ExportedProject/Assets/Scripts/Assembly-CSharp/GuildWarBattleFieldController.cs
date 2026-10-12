using System;
using System.Collections.Generic;
using GuildWar;
using UnityEngine;

public class GuildWarBattleFieldController : MonoBehaviour
{
	private class CombatMessage
	{
		public int faceId;

		public string translatedMessage;
	}

	public static GuildWarBattleFieldController instance;

	[SerializeField]
	private GuildWarBattleTimer battleTimer;

	[SerializeField]
	private UIToggle corpsDetailToggle;

	[SerializeField]
	private UIGuildWarBottomDetailInfo corpsDetailInfo;

	[SerializeField]
	private UIGuildWarBottomButtons bottomButtons;

	[SerializeField]
	private UIGuildWarMinimap minimap;

	[SerializeField]
	private UIGuildWarCorpsManagement corpsManagement;

	private List<CombatMessage> m_combatMessages;

	private void Start()
	{
	}

	private void OnDestroy()
	{
	}

	private void OnMinimapClicked(Vector3 pos)
	{
	}

	private void OnMinimapDragging(Vector3 pos)
	{
	}

	public void Init()
	{
	}

	private void FixedUpdate()
	{
	}

	private void Update()
	{
	}

	public void OnClearControllPannel()
	{
	}

	public void OnClickedCorps(bool isMyCorps)
	{
	}

	public void OnClickedTown()
	{
	}

	public void ShowCancelButton(GuildWarCombatActiveFunction activeFunction)
	{
	}

	public void ShowBuildingCameraMoveButton(Action selectFunc)
	{
	}

	public void ShowCancelButton()
	{
	}

	public void SetMyUser(GuildWar.UserData myUser)
	{
	}

	public void SetPingButtonState(int p_pingId)
	{
	}

	public void OnCorpsDetailToggleChange()
	{
	}

	public void OnDirectionCombatMessage(int faceId, string translatedMessage)
	{
	}

	public void OnDirectionPing(int p_pingId, int posX, int posY)
	{
	}

	private void UpdateCombatMessages()
	{
	}

	private string GetGrade(int lv)
	{
		return null;
	}
}
