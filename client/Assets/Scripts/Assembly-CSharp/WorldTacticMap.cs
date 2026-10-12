using System;
using UnityEngine;

public sealed class WorldTacticMap : MonoBehaviour
{
	[Serializable]
	public class StateColorInfo
	{
		public int stateId;

		public Color stateColor;
	}

	public Transform mapParent;

	public GameObject stateName;

	public UIButton showStateNameButton;

	public GameObject checkStateName;

	public UIButton showPopularityButton;

	public GameObject checkPopularity;

	public GameObject popularity;

	public UILabelLocalized popularityState;

	public UILabelLocalized occupationRate;

	public UILabelLocalized occupationCountLabel;

	public UISprite occupiedStateColorMark;

	public Color occupiedStateColor;

	public Color cloudedStateColor;

	public StateColorInfo[] stateColorInfos;

	private const string m_popupName = "WorldTacticMap";

	private bool m_showStateName;

	private void Start()
	{
	}

	private void ToggleStateName()
	{
	}

	private void UpdatePopularity()
	{
	}

	private void UpdateStateInfo()
	{
	}

	private Color FindStateColor(int stateId)
	{
		return default(Color);
	}

	public void Reset()
	{
	}
}
