using UnityEngine;
using UnityEngine.Events;
using UnityEngine.UI;

namespace HutongGames.PlayMaker.Actions
{
	public class UiButtonArray : FsmStateAction
	{
		public FsmEventTarget eventTarget;

		public FsmGameObject[] gameObjects;

		public FsmEvent[] clickEvents;

		[SerializeField]
		private Button[] buttons;

		[SerializeField]
		private GameObject[] cachedGameObjects;

		private UnityAction[] actions;

		private int clickedButton;

		public override void Reset()
		{
		}

		public override void OnPreprocess()
		{
		}

		private void InitButtons()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnExit()
		{
		}

		public void OnClick(int index)
		{
		}
	}
}
