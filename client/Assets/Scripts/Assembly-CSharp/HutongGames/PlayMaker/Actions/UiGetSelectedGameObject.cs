using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class UiGetSelectedGameObject : FsmStateAction
	{
		public FsmGameObject StoreGameObject;

		public FsmEvent ObjectChangedEvent;

		public bool everyFrame;

		private GameObject lastGameObject;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		private void GetCurrentSelectedGameObject()
		{
		}
	}
}
