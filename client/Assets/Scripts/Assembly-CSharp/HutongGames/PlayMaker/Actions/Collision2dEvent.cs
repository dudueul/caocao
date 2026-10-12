using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class Collision2dEvent : FsmStateAction
	{
		public FsmOwnerDefault gameObject;

		public Collision2DType collision;

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

		private void StoreCollisionInfo(Collision2D collisionInfo)
		{
		}

		public override void DoCollisionEnter2D(Collision2D collisionInfo)
		{
		}

		public override void DoCollisionStay2D(Collision2D collisionInfo)
		{
		}

		public override void DoCollisionExit2D(Collision2D collisionInfo)
		{
		}

		public override void DoParticleCollision(GameObject other)
		{
		}

		private void CollisionEnter2D(Collision2D collisionInfo)
		{
		}

		private void CollisionStay2D(Collision2D collisionInfo)
		{
		}

		private void CollisionExit2D(Collision2D collisionInfo)
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
