using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

public class ScenarioUI : MonoBehaviour
{
	private enum endingFrames
	{
		chronicle_frame_f_s = 0,
		chronicle_frame_f_n = 1
	}

	private enum normalEnterFrames
	{
		chronicle_frame_y_s = 0,
		chronicle_frame_y_n = 1
	}

	private enum normalLockFrames
	{
		chronicle_frame_b_s = 0,
		chronicle_frame_b_n = 1
	}

	public Action UserScenarioUpdated;

	private UserScenario m_userScenario;

	private ObscuredInt m_scenarioId;

	private EnumData.ScenarioModeLevel _mode;

	public EnumData.ScenarioModeUI uiMode;

	private readonly Color focusedColor;

	private readonly Color nonFocusedColor;

	private readonly Color nonFocusedLockedColor;

	public Action<ScenarioUI> OnClickCallback;

	public Action<ScenarioUI> OnSelectedCallback;

	public Action<ScenarioUI> OnDeselectedCallback;

	public Action<ScenarioUI> OnDoubleClickCallback;

	public Dictionary<int, GameObject> flowLines;

	private UITexture scenarioImgUI
	{
		get
		{
			return null;
		}
	}

	private Transform titleBgUI
	{
		get
		{
			return null;
		}
	}

	private UISprite titleBgSprite
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized titleLabel
	{
		get
		{
			return null;
		}
	}

	private Transform titleShadow
	{
		get
		{
			return null;
		}
	}

	private UISprite gradeUI1
	{
		get
		{
			return null;
		}
	}

	private UISprite gradeUI2
	{
		get
		{
			return null;
		}
	}

	private UISprite gradeUI3
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized releasedDateLabel
	{
		get
		{
			return null;
		}
	}

	private Transform lockBG
	{
		get
		{
			return null;
		}
	}

	private GameObject colorR
	{
		get
		{
			return null;
		}
	}

	private GameObject colorB
	{
		get
		{
			return null;
		}
	}

	private UISprite frame
	{
		get
		{
			return null;
		}
	}

	private GameObject finaleDeco
	{
		get
		{
			return null;
		}
	}

	private Transform effect
	{
		get
		{
			return null;
		}
	}

	private Transform shadow
	{
		get
		{
			return null;
		}
	}

	public UserScenario userScenario
	{
		get
		{
			return null;
		}
		private set
		{
		}
	}

	public ObscuredInt scenarioId
	{
		get
		{
			return default(ObscuredInt);
		}
		set
		{
		}
	}

	public Scenario scenarioData
	{
		get
		{
			return null;
		}
	}

	public int currentAmbition
	{
		get
		{
			return 0;
		}
	}

	public void UpdateUI(int scenarioId, EnumData.ScenarioModeLevel mode)
	{
	}

	private void UpdateUI()
	{
	}

	public void UpdateUIState()
	{
	}

	private void UpdateTitle(bool focused = false)
	{
	}

	private void UpdateGrade()
	{
	}

	private void UpdateFrame(bool focused = false)
	{
	}

	private void UpdateEffect(bool focused = false)
	{
	}

	public void OnClick()
	{
	}

	public void OnSelected()
	{
	}

	public void OnDeselected()
	{
	}

	public void OnDoubleClick()
	{
	}

	private void OnEnable()
	{
	}

	private void OnDisable()
	{
	}

	private void OnDestroy()
	{
	}
}
