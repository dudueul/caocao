using System.Collections.Generic;
using UnityEngine;

public class InstanceSlot : MonoBehaviour
{
	public delegate void Callback(InstanceSlot slot);

	private static readonly int MAX_POS_X;

	private static readonly int MAX_POS_Y;

	private Instance m_inst;

	private UILabelLocalized m_title;

	private UITexture m_rewardIcon;

	private UISprite m_eventIcon;

	private Transform m_stampContainer;

	private List<Transform> m_stampIconList;

	private Transform m_starContainer;

	private List<Transform> m_starIconList;

	private Transform m_clear;

	private Transform m_select;

	private Transform m_dim;

	private Callback m_callback;

	public Instance Inst
	{
		get
		{
			return null;
		}
	}

	private void OnDestroy()
	{
	}

	public void Initialize(Callback callback)
	{
	}

	public void UpdateUI(Instance inst)
	{
	}

	public bool IsVisible()
	{
		return false;
	}

	public void SetVisible(bool visible)
	{
	}

	public void SetPosition(int x, int y)
	{
	}

	private void Clicked()
	{
	}

	public void SetSelected(bool select)
	{
	}
}
