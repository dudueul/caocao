using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class GetAnimatorLayerCount : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmInt layerCount;

		private Animator _animator;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoGetLayerCount()
		{
		}
	}
}
