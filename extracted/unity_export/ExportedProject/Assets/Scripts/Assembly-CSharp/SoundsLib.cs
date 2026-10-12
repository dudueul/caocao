using Caocao;
using UnityEngine;

public class SoundsLib : MonoBehaviour
{
	public const string SOUND_PATH = "Sound/";

	public const string SONND_DEFAULT_OBJECT_NAME = "One shot audio";

	private const float PAN_LEVEL = 0.1f;

	public static AudioClip m_audioclip_confirm
	{
		get
		{
			return null;
		}
	}

	public static AudioClip m_audioclip_close
	{
		get
		{
			return null;
		}
	}

	public static AudioClip m_audioclip_cancel
	{
		get
		{
			return null;
		}
	}

	public static AudioClip m_audioclip_fail
	{
		get
		{
			return null;
		}
	}

	public static AudioClip m_audioclip_gamestart
	{
		get
		{
			return null;
		}
	}

	public static AudioClip m_audioclip_atk
	{
		get
		{
			return null;
		}
	}

	public static AudioClip m_audioclip_art
	{
		get
		{
			return null;
		}
	}

	public static AudioClip m_audioclip_unit_levup
	{
		get
		{
			return null;
		}
	}

	public static AudioClip m_audioclip_unit_classup
	{
		get
		{
			return null;
		}
	}

	public static AudioClip m_audioclip_unit_statup
	{
		get
		{
			return null;
		}
	}

	public static AudioClip m_audioclip_item_levup
	{
		get
		{
			return null;
		}
	}

	public static AudioClip m_audioclip_dead
	{
		get
		{
			return null;
		}
	}

	public static AudioClip m_audioclip_normal_atk_miss
	{
		get
		{
			return null;
		}
	}

	public static AudioClip m_audioclip_critical_atk_miss
	{
		get
		{
			return null;
		}
	}

	public static AudioClip m_audioclip_critical_atk
	{
		get
		{
			return null;
		}
	}

	public static AudioClip m_audioclip_normal_atk_success
	{
		get
		{
			return null;
		}
	}

	public static AudioClip m_audioclip_critical_atk_success
	{
		get
		{
			return null;
		}
	}

	public static AudioClip m_audioclip_art_name
	{
		get
		{
			return null;
		}
	}

	public static AudioClip m_audioclip_use_item
	{
		get
		{
			return null;
		}
	}

	public static AudioClip m_audioclip_art_miss
	{
		get
		{
			return null;
		}
	}

	public static AudioClip m_audioclip_cash_buy14
	{
		get
		{
			return null;
		}
	}

	public static AudioClip m_audioclip_clear_cloud
	{
		get
		{
			return null;
		}
	}

	public static AudioClip m_audioclip_notice_m34
	{
		get
		{
			return null;
		}
	}

	public static AudioClip m_audioclip_tooltip
	{
		get
		{
			return null;
		}
	}

	public static AudioClip m_audioclip_popupOn
	{
		get
		{
			return null;
		}
	}

	public static AudioClip m_audioclip_popupOff
	{
		get
		{
			return null;
		}
	}

	public static AudioClip m_audioclip_getItem
	{
		get
		{
			return null;
		}
	}

	public static AudioClip m_audioclip_unitLvGet
	{
		get
		{
			return null;
		}
	}

	public static AudioClip m_audioclip_tempation1
	{
		get
		{
			return null;
		}
	}

	public static AudioClip m_audioclip_stampOn
	{
		get
		{
			return null;
		}
	}

	public static AudioClip m_audioclip_enter
	{
		get
		{
			return null;
		}
	}

	public static AudioClip m_audioclip_m_clear
	{
		get
		{
			return null;
		}
	}

	public static AudioClip m_audioclip_btnClick
	{
		get
		{
			return null;
		}
	}

	public static AudioClip audioclip_find
	{
		get
		{
			return null;
		}
	}

	public static AudioClip audioclip_equip
	{
		get
		{
			return null;
		}
	}

	public static AudioClip GetAudioClip(string clipName)
	{
		return null;
	}

	public static AudioClip GetTownBGM(int townId)
	{
		return null;
	}

	public static AudioClip GetMoveSoundEffect(int jobId, int tileId)
	{
		return null;
	}

	public static AudioClip BuildMagicSE(string seName)
	{
		return null;
	}

	public static AudioClip GetConditionSoundEffect(int conditionId, bool isCast, bool isSpeedyAnimation)
	{
		return null;
	}

	public static AudioClip GetWeatherSoundEffect(Caocao.Weather weatherId)
	{
		return null;
	}

	public static AudioClip GetEndPopupSoundEffect(bool isWin)
	{
		return null;
	}

	public static AudioClip GetBuildingUpgradeSE(int townID, EnumData.TownBuildingType buildingType)
	{
		return null;
	}

	public static AudioClip GetUnitVoice(GameObject unitObj)
	{
		return null;
	}

	public static AudioClip GetUnitVoice(UserUnit unitUnit)
	{
		return null;
	}

	public static AudioClip GetUnitVoice(int unitId, int jobID)
	{
		return null;
	}

	public static AudioClip GetResource(int soundAliasID, int soundIndex)
	{
		return null;
	}

	public static AudioClip GetResource(int soundAliasID, ref bool isVoice)
	{
		return null;
	}
}
