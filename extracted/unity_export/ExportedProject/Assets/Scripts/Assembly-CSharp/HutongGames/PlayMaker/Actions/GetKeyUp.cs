using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class GetKeyUp : FsmStateAction
	{
		[RequiredField]
		public KeyCode key;

		public FsmEvent sendEvent;

		public FsmBool storeResult;

		public override void Reset()
		{
		}

		public override void OnUpdate()
		{
		}
	}
}
