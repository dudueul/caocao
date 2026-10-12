using System;
using System.Collections.Generic;

namespace GuildWar
{
	public abstract class CameraData : IDataTableOwner, IDataType<CameraData>, IClientDataOwner<CameraData.ClientData>, IDataRefAble<CameraData>, IDataType, IDataUpdateListener<CameraData>
	{
		public class ClientData : IClientDataType
		{
			public DataRef<WorldData> world;

			public int pos_x;

			public int pos_y;

			public int half_width;

			public int half_height;
		}

		private HashSet<IDataRefInternal<CameraData>> m_dataRefs;

		public WorldData world
		{
			get
			{
				return null;
			}
		}

		public int pos_x
		{
			get
			{
				return 0;
			}
		}

		public int pos_y
		{
			get
			{
				return 0;
			}
		}

		public int half_width
		{
			get
			{
				return 0;
			}
		}

		public int half_height
		{
			get
			{
				return 0;
			}
		}

		public abstract Action<CameraData> onUpdate { get; set; }

		public abstract ClientData clientData { get; }

		protected void OnInited(IDataContainerCreator factory)
		{
		}

		protected void OnModified(IDataContainerLinker factory)
		{
		}

		protected void OnDestroy()
		{
		}

		public void SetCamera(int posX, int posY, int halfWidth, int halfHeight)
		{
		}

		public abstract DataTables GetDataTables();

		public void AddDataRef(IDataRefInternal<CameraData> dataRef)
		{
		}

		public void RemoveDataRef(IDataRefInternal<CameraData> dataRef)
		{
		}

		public void ResetDataRefsAll()
		{
		}
	}
}
