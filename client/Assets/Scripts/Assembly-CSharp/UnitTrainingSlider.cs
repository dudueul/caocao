using UnityEngine;

public class UnitTrainingSlider : MonoBehaviour
{
	private PanelUnitTraining m_trainingPanel;

	public UISlider m_slider;

	public PressableButton m_minus;

	public PressableButton m_plus;

	public UILabelLocalized m_pointLabel;

	public UILabelLocalized m_availableAmount;

	public Transform m_resetTransf;

	private UserUnit userUnit;

	private UserItem userTrainItem;

	private EnumData.UnitStatUpType statType;

	private int maxTrainingCount;

	private int trainingItemId;

	private bool useGoldStep;

	private bool isRedistributionMode;

	private int redistributionCurrent;

	private int min;

	private int max;

	private int step;

	private int currentStart;

	private int current;

	private int prevSliderValue;

	private const float SLIDER_SOUND_GAP = 0.05f;

	private float lastSliderSoundTime;

	private static string limitedColor;

	private static string overmaxColor;

	private static string normalColor;

	private int trainableAmount
	{
		get
		{
			return 0;
		}
	}

	private int currentTrainedStat
	{
		get
		{
			return 0;
		}
	}

	private int totalAvailableAmount
	{
		get
		{
			return 0;
		}
	}

	public void UpdateUI(UserUnit userUnit, EnumData.UnitStatUpType statType)
	{
	}

	public void UpdateUI(UserUnit userUnit, EnumData.UnitStatUpType statType, bool redistributionMode)
	{
	}

	public void UpdateRedistributionCurrent(GeneralStat trainedStat)
	{
	}

	private void InitValues()
	{
	}

	private void OnValueUpdated()
	{
	}

	private void InitSlider()
	{
	}

	private void UpdateSliderValue()
	{
	}

	private void OnSliderUpdated()
	{
	}

	private void OnPlus(int delta)
	{
	}

	private void OnMinus(int delta)
	{
	}

	private void UpdatePointLabel(int value)
	{
	}

	private void UpdateRedistributionAlarmTransf()
	{
	}

	private static int GetTrainItemId(EnumData.UnitStatUpType statType)
	{
		return 0;
	}
}
