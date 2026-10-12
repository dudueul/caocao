using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class GameObjectChanged : FsmStateAction
	{
		[RequiredField]
		public FsmGameObject gameObjectVariable;

		public FsmEvent changedEvent;

		public FsmBool storeResult;

		private GameObject previousValue;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}
	}
}
