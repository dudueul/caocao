using UnityEngine;

public class ClickMiniMap : CaocaoUIButton
{
	private MiniMap m_miniMap;

	private UITexture m_miniMapTexture;

	private Vector3 m_prevCameraPos;

	private void Start()
	{
	}

	private void OnEnable()
	{
	}

	protected override void OnDestroy()
	{
	}

	private void OnMiniMapInitCompleted(RenderTexture renderTexture)
	{
	}

	private void OnTurnReverted()
	{
	}

	protected override void UpdateButtonState()
	{
	}

	protected override void OnClickImpl()
	{
	}

	public void MoveMinimapBtn(Vector3 toPos)
	{
	}

	private void ShowImportantArea()
	{
	}
}
