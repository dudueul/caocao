using UnityEngine;

public class DebugSetting : MonoBehaviour
{
	public float windowPosX;

	public float windowPosY;

	private static bool doResetWindowPos;

	private Rect windowRect;

	public static bool isShowTileBorder;

	public static bool isStepByStepAIMode;

	public static bool isShowGridPos;

	public static bool isShowUnreachable;

	public static bool isShowEventInfo;

	private bool isVisible;

	private bool isScenarioMap;

	private bool isBattleMap;

	private ControlFlow controlFlow;

	private string title;

	public Color backgoundColor;

	private GUIStyle fpsLabelStyle;

	private bool firstCall;

	private void Awake()
	{
	}

	private void Start()
	{
	}

	public static void ResetWindowPos()
	{
	}

	private void DoMyWindow(int windowID)
	{
	}

	private void OnDestroy()
	{
	}

	private void SavePref()
	{
	}

	private void ToggleDebugSettings()
	{
	}

	private void ShowCannotMoveTile()
	{
	}
}
