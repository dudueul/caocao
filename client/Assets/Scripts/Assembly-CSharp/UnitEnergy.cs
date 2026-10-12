using System;
using UnityEngine;

public class UnitEnergy : MonoBehaviour
{
	public UISprite m_gaugeBarUI;

	public UISprite m_gaugeDefaultBarUI;

	public UILabelLocalized m_gaugeValueUI;

	public UIButton m_btnRecoverEnergy;

	private UserUnit m_userUnit;

	private void Start()
	{
	}

	private void OnEnable()
	{
	}

	private void OnDisable()
	{
	}

	private void UpdateTimer()
	{
	}

	public void UpdateUi(UserUnit userUnit, bool battleDeck)
	{
	}

	private void UpdateUi(bool battleDeck)
	{
	}

	private void SetGauge(int value, int maxValue)
	{
	}

	private void RecoverUnitEnergy()
	{
	}

	private void UpdateEnergyTimer(TimeSpan timeLeft)
	{
	}
}
