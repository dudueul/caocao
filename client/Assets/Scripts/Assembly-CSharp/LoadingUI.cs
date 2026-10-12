using UnityEngine;

public class LoadingUI : MonoBehaviour
{
	public UISprite loadingGauge;

	public UILabelLocalized loadingDesc;

	public UILabelLocalized tipLabel;

	public UILabelLocalized percentageLabel;

	private GaugeScaleTween gaugeTween;

	public UITexture loadingScene;

	public string[] loadingSceneScenario;

	public string[] loadingSceneWorldMap;

	public static LoadingUI instance;

	public static int layer
	{
		get
		{
			return 0;
		}
	}

	public static bool IsExistInstance()
	{
		return false;
	}

	private void Awake()
	{
	}

	private void Start()
	{
	}

	public void SetLoadingDescString(string desc)
	{
	}

	public void SetLoadingPercentage(float percentage, bool instantly = false)
	{
	}

	public void SetLoadingProgressActive(bool active)
	{
	}

	private void OnGetCustomLoadingImage(Texture texture)
	{
	}

	private void SetLoadingSceneTextureNormal(string imgName)
	{
	}

	private void SetLoadingSceneTextureCustom(string customLoadingImgPath)
	{
	}

	public void SetUI(string from, string to, string customImgPath)
	{
	}

	public int GetLoadingEntry(string from, string to)
	{
		return 0;
	}

	private void Update()
	{
	}

	private void OnDestroy()
	{
	}
}
