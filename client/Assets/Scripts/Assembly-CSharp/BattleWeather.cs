using System;
using System.Collections;
using System.Diagnostics;
using System.Text;
using Caocao;
using UnityEngine;

public class BattleWeather : SceneSingleton<BattleWeather>
{
	private Transform m_weatherEffectsParentOnCamera;

	private Transform m_weatherEffectsParentOnWorld;

	private int m_climateID;

	private Caocao.Weather m_weatherID;

	public Action OnWeatherChanged;

	private GameObject m_weatherToolTipObj;

	private Array m_weatherValues;

	private int[] m_weatherType;

	private static string ms_formatter;

	private static StringBuilder m_stringBuilder;

	public int climateID
	{
		get
		{
			return 0;
		}
	}

	public Caocao.Weather weatherID
	{
		get
		{
			return (Caocao.Weather)0;
		}
	}

	private void Awake()
	{
	}

	public void InitWeather()
	{
	}

	private Caocao.Weather GetNewWeather()
	{
		return (Caocao.Weather)0;
	}

	public void SetWeather(Caocao.Weather newWeatherID, bool skipUI = false)
	{
	}

	private void ChangeWorldEffect()
	{
	}

	private void PlaySoundClip()
	{
	}

	private void ChangeCameraEffect()
	{
	}

	private void RemovePrevWeatherEffect(Transform targetUI)
	{
	}

	protected override void OnDestroy()
	{
	}

	private void ShowWeatherInfo()
	{
	}

	[DebuggerHidden]
	private IEnumerator ShowWeatherTooltip()
	{
		return null;
	}

	private void SetWeatherGuide(Caocao.Weather weather, GameObject target)
	{
	}

	public static string GetWeatherGuide(Caocao.Weather weather)
	{
		return null;
	}

	public float GetFireIgnitionRate()
	{
		return 0f;
	}

	public float GetFireIgnitionRate(Caocao.Weather weather)
	{
		return 0f;
	}

	public float GetFireSpreadRate()
	{
		return 0f;
	}

	public static float GetFireSpreadRate(Caocao.Weather weather)
	{
		return 0f;
	}

	public float GetFireExtinguishRate()
	{
		return 0f;
	}

	public static float GetFireExtinguishRate(Caocao.Weather weather)
	{
		return 0f;
	}
}
