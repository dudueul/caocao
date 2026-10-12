using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class DestroyComponent : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmString component;

		private Component aComponent;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoDestroyComponent(GameObject go)
		{
		}
	}
}
