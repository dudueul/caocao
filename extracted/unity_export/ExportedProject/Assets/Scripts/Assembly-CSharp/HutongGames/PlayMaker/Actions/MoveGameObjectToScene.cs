using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class MoveGameObjectToScene : GetSceneActionBase
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmBool findRootIfNecessary;

		public FsmBool success;

		public FsmEvent successEvent;

		public FsmEvent failureEvent;

		private GameObject _go;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}
	}
}
