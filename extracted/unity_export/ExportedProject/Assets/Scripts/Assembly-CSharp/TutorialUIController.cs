using System.Collections.Generic;
using LitJson;
using UnityEngine;

public class TutorialUIController : Singleton<TutorialUIController>
{
	private List<UITutorial> m_uiTutorials;

	private List<string> m_parents;

	private Dictionary<string, Dictionary<string, List<TutorialUI>>> m_tutorialUIDic;

	[SerializeField]
	private bool m_showInfo;

	private bool m_willSkipTutorial;

	private readonly string[] m_roots;

	private bool m_willSkipTutorialBattle;

	private bool m_init;

	public bool willSkipTutorial
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool willSkipTutorialBattle
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	protected override void Awake()
	{
	}

	private bool Init()
	{
		return false;
	}

	public void ClearUITutorials()
	{
	}

	public void RefreshUI()
	{
	}

	public bool DidFinishTutorial()
	{
		return false;
	}

	public void CollectUI()
	{
	}

	public void CheckUITutorial(GameObject target)
	{
	}

	public bool IsTutorialUIParents(string rootName)
	{
		return false;
	}

	private UITutorial AddUITutorial(Transform root, TutorialUI tutorialUI)
	{
		return null;
	}

	public void SkipTutorialBattle()
	{
	}

	public void SkipTutorial()
	{
	}

	private void OnFlagSaveResult(JsonData response)
	{
	}

	public bool CheckCondition(GameObject obj, TutorialUI tutorialUI)
	{
		return false;
	}

	private bool CheckAttatchButtonEffect(GameObject obj, TutorialUI tutorialUI)
	{
		return false;
	}

	public bool CheckCondition(TutorialUI tutorialUI)
	{
		return false;
	}
}
