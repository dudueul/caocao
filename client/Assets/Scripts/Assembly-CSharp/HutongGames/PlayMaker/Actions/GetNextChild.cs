using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class GetNextChild : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmGameObject storeNextChild;

		public FsmEvent loopEvent;

		public FsmEvent finishedEvent;

		public FsmBool resetFlag;

		private GameObject go;

		private int nextChildIndex;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoGetNextChild(GameObject parent)
		{
		}
	}
}
