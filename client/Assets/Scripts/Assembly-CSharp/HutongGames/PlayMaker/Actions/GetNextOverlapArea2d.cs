using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class GetNextOverlapArea2d : FsmStateAction
	{
		public FsmOwnerDefault firstCornerGameObject;

		public FsmVector2 firstCornerPosition;

		public FsmGameObject secondCornerGameObject;

		public FsmVector2 secondCornerPosition;

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

		private Collider2D[] GetOverlapAreaAll()
		{
			return null;
		}
	}
}
