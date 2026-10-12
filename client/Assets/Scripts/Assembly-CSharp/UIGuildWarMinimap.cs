using System;
using GuildWar;
using UnityEngine;

public class UIGuildWarMinimap : MonoBehaviour
{
	public enum MinimapState
	{
		None = 0,
		MinimapOpened = 1,
		MinimapClosed = 2
	}

	public enum ButtonState
	{
		None = 0,
		Exclam = 1,
		Flag = 2
	}

	[SerializeField]
	private GameObject m_gameObjectMinimap;

	[SerializeField]
	private GameObject m_gameObjectButtons;

	[SerializeField]
	private int m_fixedWidth;

	[SerializeField]
	private UITexture m_BGRenderer;

	[SerializeField]
	private UITexture m_FogRenderer;

	[SerializeField]
	private UITexture m_ObjectRenderer;

	[SerializeField]
	private UIWidget m_CameraAreaDummy;

	[SerializeField]
	private UIGuildWarMinimapCameraArea m_CameraArea;

	[SerializeField]
	private UIWidget m_PingsDummy;

	[SerializeField]
	private GameObject m_Pings;

	[SerializeField]
	private GameObject m_MinimapExclamPing;

	[SerializeField]
	private GameObject m_MinimapFlagPing;

	[SerializeField]
	private GameObject m_MinimapCancelPing;

	[SerializeField]
	private GameObject m_MinimapPingExclamSelected;

	[SerializeField]
	private GameObject m_MinimapPingFlagSelected;

	[SerializeField]
	private GameObject m_MinimapPingExclamDim;

	[SerializeField]
	private GameObject m_MinimapPingFlagDim;

	[SerializeField]
	private GameObject m_ButtonsOpenMinimap;

	[SerializeField]
	private GameObject m_ButtonsExclamPing;

	[SerializeField]
	private GameObject m_ButtonsFlagPing;

	[SerializeField]
	private GameObject m_ButtonsCancelPing;

	[SerializeField]
	private GameObject m_ButtonsPingExclamSelected;

	[SerializeField]
	private GameObject m_ButtonsPingFlagSelected;

	[SerializeField]
	private GameObject m_ButtonsPingExclamDim;

	[SerializeField]
	private GameObject m_ButtonsPingFlagDim;

	private float m_cameraSize;

	private Vector2 m_min;

	private Vector2 m_max;

	private int m_guiLayer;

	public Action<Vector3> onMinimapClicked;

	public Action<Vector3> onMinimapDragging;

	private MinimapState m_minimapState;

	private ButtonState m_buttonState;

	private bool m_hasMyUser;

	public int GUILayer
	{
		get
		{
			return 0;
		}
	}

	public int fixedWidth
	{
		get
		{
			return 0;
		}
	}

	public void Init()
	{
	}

	private void OnMinimapClicked(Vector3 pos)
	{
	}

	private void OnMinimapDragging(Vector3 pos)
	{
	}

	public void SetMinimapUISize(int width, int height)
	{
	}

	public void SetMinimapTileSize(GuildWarMapInfo guildWarMapInfo, float cameraSize)
	{
	}

	private Vector2 GetUnitScalePosition(Vector2 worldPosition)
	{
		return default(Vector2);
	}

	public void AddPing(int p_pingId, Vector2 worldPosition)
	{
	}

	private void DisposePings()
	{
	}

	private void SetLayerChildrenAll(GameObject gameObject, int layer)
	{
	}

	private void InitMinimapState()
	{
	}

	private void SetMinimapState(MinimapState state)
	{
	}

	private void InitButtonState()
	{
	}

	public void SetMyUser(GuildWar.UserData userData)
	{
	}

	public void SetButtonState(int p_pingId)
	{
	}

	private void UpdateButtonState()
	{
	}

	public void StartPingExclam()
	{
	}

	public void StartPingFlag()
	{
	}

	public void CancelPing()
	{
	}

	public void ShowMinimap()
	{
	}

	public void CloseMinimap()
	{
	}

	private Vector3 ScreenPositionToUnitScalePosition(Vector3 pos)
	{
		return default(Vector3);
	}

	public void OnDrag()
	{
	}

	public void OnClick()
	{
	}
}
