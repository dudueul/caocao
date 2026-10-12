namespace HutongGames.PlayMaker.Actions
{
	public class CreateObject : FsmStateAction
	{
		[RequiredField]
		public FsmGameObject gameObject;

		public FsmGameObject spawnPoint;

		public FsmVector3 position;

		public FsmVector3 rotation;

		public FsmGameObject storeObject;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}
	}
}
