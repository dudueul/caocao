using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class UITutorialInfo
{
	public bool enable;

	public int saveFlagId;

	public EnumData.CompareSign compare;

	public int value;

	public UITutorial.Action action;

	public string script;

	public List<UnityEngine.Object> applied;

	public List<Color> colors;

	public TutorialUI tutorialUI;
}
