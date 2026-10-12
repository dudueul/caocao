using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class CollisionEvent : FsmStateAction
	{
		public FsmOwnerDefault gameObject;

		public CollisionType collision;

		public FsmString collideTag;

		public FsmEvent sendEvent;

		public FsmGameObject storeCollider;

		public FsmFloat storeForce;

		private PlayMakerProxyBase cachedProxy;

		public override void Reset()
		{
		}

		public override void OnPreprocess()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnExit()
		{
		}

		private void UpdateCallback()
		{
		}

		private void GetProxyComponent()
		{
		}

		private void AddCallback()
		{
		}

		private void RemoveCallback()
		{
		}

		private void StoreCollisionInfo(Collision collisionInfo)
		{
		}

		public override void DoCollisionEnter(Collision collisionInfo)
		{
		}

		public override void DoCollisionStay(Collision collisionInfo)
		{
		}

		public override void DoCollisionExit(Collision collisionInfo)
		{
		}

		public override void DoControllerColliderHit(ControllerColliderHit collisionInfo)
		{
		}

		public override void DoParticleCollision(GameObject other)
		{
		}

		private void CollisionEnter(Collision collisionInfo)
		{
		}

		private void CollisionStay(Collision collisionInfo)
		{
		}

		private void CollisionExit(Collision collisionInfo)
		{
		}

		private void ControllerColliderHit(ControllerColliderHit collisionInfo)
		{
		}

		private void ParticleCollision(GameObject other)
		{
		}

		public override string ErrorCheck()
		{
			return null;
		}
	}
}
