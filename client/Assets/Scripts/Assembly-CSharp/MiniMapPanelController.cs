using UnityEngine;

public class MiniMapPanelController : CaocaoUIPanel
{
	private MiniMap m_miniMap;

	private UISprite m_cameraBorder;

	private UITexture m_miniMapTexture;

	private int m_origMiniMapTextureWidth;

	private int m_origMiniMapTextureHeight;

	private int m_appliedMiniMapTextureWidth;

	private int m_appliedMiniMapTextureHeight;

	private Rect m_miniMapRect;

	private Rect m_miniMapDeviceRect;

	private const int BorderLineThick = 5;

	private bool m_started;

	private void Start()
	{
	}

	public override void OnInit()
	{
	}

	private void OnEnable()
	{
	}

	protected override void OnDestroy()
	{
	}

	private void Update()
	{
	}

	private void MoveCameraBorder(Vector3 uiPos)
	{
	}

	private void OnMiniMapInitCompleted(RenderTexture renderTexture)
	{
	}

	private Vector3 GetScreenPosFromWorldPos(Vector3 worldPos)
	{
		return default(Vector3);
	}

	protected override void ShowPanelImpl()
	{
	}

	protected override void ClosePanelImpl()
	{
	}
}
