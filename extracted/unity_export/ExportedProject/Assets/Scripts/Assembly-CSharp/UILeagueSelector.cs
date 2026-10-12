using System;
using System.Collections.Generic;
using UnityEngine;

public class UILeagueSelector : MonoBehaviour
{
	[SerializeField]
	private UIButton prev;

	[SerializeField]
	private UIButton next;

	[SerializeField]
	private UITexture icon;

	[SerializeField]
	private UILabelLocalized desc;

	private int m_currentIndex;

	private List<League> m_leagues;

	private Action<League> m_onSelectLeague;

	public League current
	{
		get
		{
			return null;
		}
	}

	public void Clear()
	{
	}

	public void SetActive(bool active)
	{
	}

	public void AddLeague(int leagueId)
	{
	}

	public void AddLeague(League league)
	{
	}

	public void OnNext()
	{
	}

	public void OnPrev()
	{
	}

	public void OnMove(int index)
	{
	}

	public void OnSelectHandler(Action<League> onClick)
	{
	}
}
