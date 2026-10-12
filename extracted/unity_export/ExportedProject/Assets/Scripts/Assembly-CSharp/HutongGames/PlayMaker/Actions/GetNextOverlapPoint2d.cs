using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class GetNextOverlapPoint2d : FsmStateAction
	{
		public FsmOwnerDefault gameObject;

		public FsmVector2 position;

		public FsmInt minDepth;

		public FsmInt maxDepth;

		public FsmBool resetFlag;

		public FsmInt[] layerMask;

		public FsmBool invertMask;

		public FsmInt collidersCount;

		[RequiredField]
		public FsmGameObject storeNextCollider;

		public FsmEvent loopEvent;

		public FsmEvent finishedEvent;

		private Collider2D[] colliders;

		private int colliderCount;

		private int nextColliderIndex;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoGetNextCollider()
		{
		}

		private Collider2D[] GetOverlapPointAll()
		{
			return null;
		}
	}
}
