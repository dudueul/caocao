namespace HutongGames.PlayMaker.Actions
{
	public class SelectRandomGameObject : FsmStateAction
	{
		public FsmGameObject[] gameObjects;

		public FsmFloat[] weights;

		[RequiredField]
		public FsmGameObject storeGameObject;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoSelectRandomGameObject()
		{
		}
	}
}
