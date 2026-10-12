using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class GetAnimatorLayerName : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmInt layerIndex;

		[RequiredField]
		public FsmString layerName;

		private Animator _animator;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoGetLayerName()
		{
		}
	}
}
